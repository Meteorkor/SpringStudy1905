package com.meteor.dutch;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.meteor.coffee.CoffeeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="usys_dutch_manage")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DutchDao {
	@Id
	@GeneratedValue
	private long id;
	
	@Enumerated(EnumType.STRING)
	private CoffeeEnum coffeeKind;
	private Date produceTime = new Date();
	private Date expiredTime;
	
	@Size(min=5)
	private String desc;
}
