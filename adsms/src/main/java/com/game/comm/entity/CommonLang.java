package com.game.comm.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonApp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_lang")
public class CommonLang implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// Fields
		private Long id;
		private String lang;
		private String simplified;
		private String langdesc;
		private String createtime;
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "ID", unique = true, nullable = false)
		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Column(name = "LANGDESC")
		public String getLangdesc() {
			return langdesc;
		}

		public void setLangdesc(String langdesc) {
			this.langdesc = langdesc;
		}

		@Column(name = "LANG")
		public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}

		@Column(name = "SIMPLIFIED")
		public String getSimplified() {
			return simplified;
		}

		public void setSimplified(String simplified) {
			this.simplified = simplified;
		}

		@Column(name = "CREATETIME")
		public String getCreatetime() {
			return createtime;
		}

		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}

}