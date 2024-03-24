package com.beans;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CouponBean implements Serializable {
	private int couponId;
	private String couponName;
	private int couponCount;
	private Date couponExpireDate;
	private int couponDiscount;

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public Date getCouponExpireDate() {
		return couponExpireDate;
	}

	public void setCouponExpireDate(Date couponExpireDate) {
		this.couponExpireDate = couponExpireDate;
	}

	public int getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(int couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
}
