package com.softserveinc.dokazovi.entity.enumerations;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PostgreSQLUserStatusEnumType extends EnumType {

	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if(value == null) {
			st.setNull(index, Types.OTHER);
		}
		else {
			st.setObject(
					index,
					value.toString().toLowerCase(),
					Types.OTHER
			);
		}
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		String value = rs.getString(names[0]);
		if (value == null) {
			return null;
		} else {
			return UserStatus.valueOf(value.toUpperCase());
		}
	}
}
