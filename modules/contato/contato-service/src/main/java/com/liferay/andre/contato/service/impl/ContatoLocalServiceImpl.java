/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.andre.contato.service.impl;

import com.liferay.andre.contato.model.Contato;
import com.liferay.andre.contato.service.base.ContatoLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.andre.contato.model.Contato",
	service = AopService.class
)
public class ContatoLocalServiceImpl extends ContatoLocalServiceBaseImpl {

		public Contato addContato(long groupId, String nome, String telefone, String email, int idade,
								  ServiceContext serviceContext) throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);
		long userId = serviceContext.getUserId();
		User user = userLocalService.getUser(userId);

		long contatoId = counterLocalService.increment(Contato.class.getName());

		Contato contato = createContato(contatoId);

		contato.setGroupId(groupId);
		contato.setCompanyId(group.getCompanyId());
		contato.setUserId(userId);
		contato.setUserName(user.getScreenName());
		contato.setCreateDate(new Date());
		contato.setModifiedDate(new Date());

		contato.setNome(nome);
		contato.setTelefone(telefone);
		contato.setEmail(email);
		contato.setIdade(idade);

		return super.addContato(contato);
	}

	public Contato updaContato(long contatoId, String nome, String telefone, String email,
							  int idade) throws PortalException {

		Contato contato = getContato(contatoId);

		contato.setModifiedDate(new Date());

		contato.setNome(nome);
		contato.setTelefone(telefone);
		contato.setEmail(email);
		contato.setIdade(idade);

		return super.updateContato(contato);
	}

	@Override
	public Contato addContato(Contato contato){
		throw new UnsupportedOperationException("NÃO SUPORTADO");
	}

	@Override
	public Contato updaContato(Contato contato){
		throw new UnsupportedOperationException("NÃO SUPORTADO");
	}
}