package test;

public class Tree {

	public boolean isGrownUp;
	
	public void grow() {
		System.out.println("ok, let's do it.");
	}

	@Override
	public String toString() {
		return "Tree [isGrownUp=" + isGrownUp + "]";
	}
	
	
}
