package com.softserveinc.dokazovi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The PlatformInformation entity is responsible for correlating with the DB's platform_information table as well as for
 * transmitting data between layers of the app. It represents a chapter of information about the platform (site) that
 * consist of a title and the content.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "platform_information_entity")
@Table(name = "platform_information")
public class PlatformInformationEntity {

	/**
	 * The identification number corresponds to the appropriate column in the DB's platform_information table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * The field "title" corresponds to the appropriate column in the DB's platform_information table and is intended
	 * for holding a piece of information - a title of a chapter.
	 */
	private String title;

	/**
	 * The field "text" corresponds to the "content" column in the DB's platform_information table and is intended for
	 * holding a piece of information (the main content of a chapter) you are submitting or requesting for.
	 */
	@Column(name = "content",columnDefinition = "TEXT")
	private String text;
}
