package joazlazer.plugins.nebulazrun.util;

import java.util.Collection;

public class PendingChange {
	public PendingChangeType type;
	public Object newObject;
	public Object oldObject;
	public Collection<? extends Object> containingCollection;
}