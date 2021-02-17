import java.io.BufferedReader;
public class mainS{
	public static call(int a){
		return a;
	}
	public static void main(String[] args){
		float a;
		int b;
		a=5.0;
		b=0;
		if(a==5){
			for(int i=0;i<5;i++){
				b=b+call(1);
			}
		}
	}
}