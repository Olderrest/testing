package com.springapp.testing.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Model {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
