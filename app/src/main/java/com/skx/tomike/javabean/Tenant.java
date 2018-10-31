package com.skx.tomike.javabean;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 房客，入住人信息类，实现了Parcelable 接口
 *
 * @author shiguotao
 */
public class Tenant implements Serializable, Cloneable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6438952142819766440L;
	private String id;// id
	private String name;// 姓名
	private String cardType;// 证件类型
	private String cardNo;// 证件号码
	private String sex;// 性别
	private String birth;// 出生日期
	private String insurance;// 是否投保(no 没有保险 paid已付保险 free 免费)
	private String idCardState;
	private String idCardStateEnum;

	public Tenant() {
		super();
	}

	@Override
	public Tenant clone() throws CloneNotSupportedException {
		Tenant tenant = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.close();

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			tenant = (Tenant) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenant;
	}

	public String getBirth() {
		if (this.birth == null || TextUtils.isEmpty(this.birth)) {
			return "";
		}
		return this.birth;
	}

	public String getCardNo() {
		if (this.cardNo == null || TextUtils.isEmpty(this.cardNo)) {
			return "";
		}
		return this.cardNo;
	}

	public String getCardType() {
		if (this.cardType == null || TextUtils.isEmpty(this.cardType)) {
			return "";
		}
		return this.cardType;
	}

	public long getId() {
		if (this.id == null || TextUtils.isEmpty(this.id)) {
			return 0;
		}
		return Long.parseLong(this.id);
	}

	public String getInsurance() {
		if (this.insurance == null || TextUtils.isEmpty(this.insurance)) {
			return "no";
		}
		return this.insurance;
	}

	public String getName() {
		if (this.name == null || TextUtils.isEmpty(this.name)) {
			return "";
		}
		return this.name;
	}

	public String getSex() {
		if (this.sex == null || TextUtils.isEmpty(this.sex)) {
			return "";
		}
		return this.sex;
	}

	public String getIdCardState() {
		return idCardState;
	}

	public void setIdCardState(String idCardState) {
		this.idCardState = idCardState;
	}

	public String getIdCardStateEnum() {
		return idCardStateEnum;
	}

	public void setIdCardStateEnum(String idCardStateEnum) {
		this.idCardStateEnum = idCardStateEnum;
	}
}
