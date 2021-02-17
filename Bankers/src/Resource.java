
public class Resource {
	private int a;
	private int b;
	private int c;
	
	public Resource() {
		this.a=0;
		this.b=0;
		this.c=0;
	}
	
	public Resource(int a, int b, int c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public void setB(int b) {
		this.b = b;
	}
	
	public void setC(int c) {
		this.c = c;
	}
	
	public int getA() {
		return this.a;
	}
	
	public int getB() {
		return this.b;
	}
	
	public int getC() {
		return this.c;
	}
	
	public boolean isZero() {
		return (this.getA() == 0 && this.getB() == 0 && this.getC() == 0);
	}
	
	public void addResource(Resource b){
		this.setA(this.getA() + b.getA());
		this.setB(this.getB() + b.getB());
		this.setC(this.getC() + b.getC());
	}
	
	public boolean greaterThan(Resource b){
		if(this.a > b.getA() || this.b > b.getB() || this.c>b.getC()){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return (this.a + "\t" + this.b + "\t" + this.c);
	}
}