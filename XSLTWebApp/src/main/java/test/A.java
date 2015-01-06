package test;

public class A {
	
	public static void main(String[] args) {
        new Child();
    }
    static class Parent {
        Parent() {
            foo();
        }
        void foo() {
            //empty method
        }
    }
    static class Child extends Parent {
        private String x = "hello-buddy";
        @Override
        void foo() {
            System.out.println(x.length());
        }
    }

}
