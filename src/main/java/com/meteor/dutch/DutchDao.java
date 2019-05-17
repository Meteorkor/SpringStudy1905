package com.meteor.dutch;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usys_dutch_manage")
@Getter
@Setter
@NoArgsConstructor
public class DutchDao {
	private long id;
	private String coffeeKind;
	private Date produceTime;
	private Date expiredTime;
}
