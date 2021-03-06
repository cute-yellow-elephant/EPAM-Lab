package com.epam.newswebapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.epam.newswebapp.util.Constants;

/**
 * The News class is a java-bean, representing a piece of news with appropriated fields.
 * Implements {@link Comparable} for comparing news by date.
 */
@Entity

@NamedQuery(name=Constants.NAMED_QUERY_DELETE_NEWSLIST_BY_IDS, 
			query="DELETE FROM News news WHERE news.id IN :idList")

@Table(name="NEWSLIST")
@SequenceGenerator(name="generator", sequenceName="NEWSLIST_SEQ", allocationSize = 1) 
public class News implements Comparable<News>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="generator")
	@Column(name = "NEWS_ID")
	private Integer id;
	
	@Column(name = "TITLE", unique = true)
	private String title;
	@Temporal(value = TemporalType.DATE)
	@Column(name = "NEWS_DATE")
	private Date date;
	@Column(name = "BRIEF", unique = true)
	private String brief;
	@Column(name = "CONTENT", unique = true)
	private String content;

	public News(){}
	
	public News(News news){
		this(news.getTitle(), news.getDate(), news.getBrief(), news.getContent());
		this.id = news.getId();
	}
	
	public News(String title, Date date, String brief, String content) {
		this.id = 0;
		this.title = title;
		this.date = date;
		this.brief = brief;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", date=" + date
				+ ", brief=" + brief + ", content=" + content + "]";
	}
	
	
	
	@Override
	public int compareTo(News o) {
		return o.getDate().compareTo(getDate());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


}
