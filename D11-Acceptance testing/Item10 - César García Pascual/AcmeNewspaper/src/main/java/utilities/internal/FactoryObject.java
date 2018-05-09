package utilities.internal;

import java.util.Collection;
import java.util.List;

public class FactoryObject {

	public FactoryObject(){}
	//Attributes
	private String name;
	private String spanishName;
	private Boolean abs;
	private String ext;
	private List<String> serviceMethods;
	private List<Attribute> attributes;
	private List<Attribute> relationships;
	private List<Query>	queries;
	private Boolean list;
	private Boolean edit;
	private Boolean display;
	private String auth;
	//Nested Classes
	public class Attribute{
		public String name;
		public String type;
		public Boolean isCollection;
		public Collection<String> annotations;
		public Attribute(){}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Boolean getIsCollection() {
			return isCollection;
		}
		public void setIsCollection(Boolean isCollection) {
			this.isCollection = isCollection;
		}
		public Collection<String> getAnnotations() {
			return annotations;
		}
		public void setAnnotations(Collection<String> annotations) {
			this.annotations = annotations;
		}
	}
	public class Query{
		private String name;
		private List<Input> inputs;
		private String output;
		private String queryString;
		private String comment;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Input> getInputs() {
			return inputs;
		}
		public void setInputs(List<Input> inputs) {
			this.inputs = inputs;
		}
		public String getOutput() {
			return output;
		}
		public void setOutput(String output) {
			this.output = output;
		}
		public String getQueryString() {
			return queryString;
		}
		public void setQueryString(String queryString) {
			this.queryString = queryString;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
	}
	public class Input{
		private String name;
		private String type;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpanishName() {
		return spanishName;
	}
	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}
	public Boolean getAbs() {
		return abs;
	}
	public void setAbs(Boolean abs) {
		this.abs = abs;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public List<String> getServiceMethods() {
		return serviceMethods;
	}
	public void setServiceMethods(List<String> serviceMethods) {
		this.serviceMethods = serviceMethods;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public List<Attribute> getRelationships() {
		return relationships;
	}
	public void setRelationships(List<Attribute> relationships) {
		this.relationships = relationships;
	}
	public List<Query> getQueries() {
		return queries;
	}
	public void setQueries(List<Query> queries) {
		this.queries = queries;
	}
	public Boolean getList() {
		return list;
	}
	public void setList(Boolean list) {
		this.list = list;
	}
	public Boolean getEdit() {
		return edit;
	}
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	public Boolean getDisplay() {
		return display;
	}
	public void setDisplay(Boolean display) {
		this.display = display;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
}
