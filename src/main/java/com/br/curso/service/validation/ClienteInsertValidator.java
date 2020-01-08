package com.br.curso.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.br.curso.domain.enuns.TipoCliente;
import com.br.curso.dto.ClienteNewDTO;
import com.br.curso.resource.exceptions.FieldMessage;
import com.br.curso.service.util.Validator;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public boolean isValid(ClienteNewDTO obj, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();

		if (obj.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !Validator.validarCPF(obj.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if (obj.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !Validator.validarCNPJ(obj.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
