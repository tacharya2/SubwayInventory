package com.subwayInventory.subway.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subway_items")
public class SubwayItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "frozen")
	private String frozen;

	@Column(name = "cooler")
	private String cooler;

	@Column(name = "dry")
	private String dryString;

	@Column(name = "cleaning_supplies")
	private  String cleaningSupplie;

	@Column(name = "sandwich_unit")
	private  String sandwichUnit;

	/**
	 * @param id
	 * @param frozen
	 * @param cooler
	 * @param dryString
	 * @param cleaningSupplie
	 * @param sandwichUnit
	 */
	public SubwayItems(String frozen, String cooler, String dryString, String cleaningSupplie,
			String sandwichUnit) {
		super();

		this.frozen = frozen;
		this.cooler = cooler;
		this.dryString = dryString;
		this.cleaningSupplie = cleaningSupplie;
		this.sandwichUnit = sandwichUnit;
	}
	public SubwayItems() {

	}

	public int getId() {
		return id;
	}

	public String getFrozen() {
		return frozen;
	}

	public String getCooler() {
		return cooler;
	}

	public String getDryString() {
		return dryString;
	}

	public String getCleaningSupplie() {
		return cleaningSupplie;
	}

	public String getSandwichUnit() {
		return sandwichUnit;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}

	public void setCooler(String cooler) {
		this.cooler = cooler;
	}

	public void setDryString(String dryString) {
		this.dryString = dryString;
	}

	public void setCleaningSupplie(String cleaningSupplie) {
		this.cleaningSupplie = cleaningSupplie;
	}

	public void setSandwichUnit(String sandwichUnit) {
		this.sandwichUnit = sandwichUnit;
	}

	@Override
	public String toString() {
		return "SubwayItems [id=" + id + ", frozen=" + frozen + ", cooler=" + cooler + ", dryString=" + dryString
				+ ", cleaningSupplie=" + cleaningSupplie + ", sandwichUnit=" + sandwichUnit + "]";
	}
}