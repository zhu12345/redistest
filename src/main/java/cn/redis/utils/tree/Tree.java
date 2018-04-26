package cn.redis.utils.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	private int id;
	private int tag;
	private String parentString;
	private String state;
	private int parentId;
	private String text;
	private List<Tree> lTree = new ArrayList<Tree>();
	public Tree(int id, int tag, String parentString, String text, int parentId) {
		this(id, tag, parentString, text);
		this.parentId = parentId;
	}
	public Tree(int id, int tag, String parentString, String text) {
		this.id = id;
		this.tag = tag;
		this.text = text;
		this.parentString = parentString;
	}
	public Tree(int id, int tag, String parentString, String text, int parentId, String state) {
		this(id, tag, parentString, text, parentId);
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public List<Tree> getlTree() {
		return lTree;
	}
	public void setlTree(List<Tree> lTree) {
		this.lTree = lTree;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentString() {
		return parentString;
	}
	public void setParentString(String parentString) {
		this.parentString = parentString;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
