package joazlazer.plugins.nebulazrun.util;

import java.util.Collection;

public class PendingChange {
	public PendingChangeType type;
    public Object newObject;
    public Object oldObject;
	public Collection<? extends Object> containingCollection;
	public String changeID;
	public Object sender;
	
	public PendingChange setNewObject(Object par0) {
		newObject = par0;
		return this;
	}
	
	public PendingChange setOldObject(Object par0) {
		oldObject = par0;
		return this;
	}
	
	public PendingChange setContainingCollection(Collection<? extends Object> par0) {
		containingCollection = par0;
		return this;
	}
	
	public PendingChange setType(PendingChangeType par0) {
		type = par0;
		return this;
	}
	
	public PendingChange setChangeID(String id) {
		changeID = id;
		return this;
	}
	
	public PendingChange setSender(Object sender) {
		this.sender = sender;
		return this;
	}
}