package com.br.curso.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.br.curso.domain.Cidade;
import com.br.curso.domain.Cliente;
import com.br.curso.domain.Endereco;
import com.br.curso.domain.enuns.Perfil;
import com.br.curso.domain.enuns.TipoCliente;
import com.br.curso.dto.ClienteDTO;
import com.br.curso.dto.ClienteNewDTO;
import com.br.curso.repository.ClienteRepository;
import com.br.curso.repository.EnderecoRepository;
import com.br.curso.security.UserSS;
import com.br.curso.service.exception.AuthorizationException;
import com.br.curso.service.exception.DataIntegratyException;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private S3Service s3Service;

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO obj) {
		Cliente cli = new Cliente();
		// criptografa a senha
		cli.setSenha(pe.encode(obj.getSenha()));
		cli.setNome(obj.getNome());
		cli.setEmail(obj.getEmail());
		cli.setCpfOuCnpj(obj.getCpfOuCnpj());
		cli.setTipo(TipoCliente.toEnum(obj.getTipo()));
		cli.getTelefones().addAll(Arrays.asList(obj.getTelefone1()));
		if (obj.getTelefone2() != null) {
			cli.getTelefones().addAll(Arrays.asList(obj.getTelefone2()));
		}
		if (obj.getTelefone3() != null) {
			cli.getTelefones().addAll(Arrays.asList(obj.getTelefone3()));
		}

		// seta os dados da cidade
		Cidade cidade = new Cidade();
		cidade.setId(obj.getCidadeId());
		// seta os dados do endereço.
		Endereco end = new Endereco();
		end.setLogradouro(obj.getLogradouro());
		end.setBairro(obj.getBairro());
		end.setCidade(cidade);
		end.setCep(obj.getCep());
		end.setCliente(cli);
		end.setComplemento(obj.getComplemento());
		end.setNumero(obj.getNumero());
		cli.getEnderecos().addAll(Arrays.asList(end));

		return cli;
	}

	@Transactional
	public Cliente salvar(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public void excluir(Integer id) {
		try {
			repository.deleteById(id);
		}catch(DataIntegratyException e) {
			throw new DataIntegratyException("Não é possível exclui o cliente, o mesmo possui pedido relacionados");
		}
	}

	public List<Cliente> listar() {
		return repository.findAll();
	}

	public Cliente buscar(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !user.getId().equals(id)) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Cliente> obj = repository.findById(id);
		if (obj.isPresent() == false) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj.get();
	}

	public Cliente atualizar(Cliente obj) {
		Cliente newObj = buscar(obj.getId());
		atualizarDados(newObj, obj);
		return repository.save(newObj);
	}

	private void atualizarDados(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Page<Cliente> listarPaginacao(Integer page, Integer linesPerPage, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		return repository.findAll(pageRequest);
	}

	
	public URI uploadFotoPerfil(MultipartFile multiPart) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		URI uri = s3Service.uploadFile(multiPart);
		Cliente cli = this.repository.findById(user.getId()).get();
		cli.setImageURL(uri.toString());
		this.repository.save(cli);
		return uri;
	}
}
