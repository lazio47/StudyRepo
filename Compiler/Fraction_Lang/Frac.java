public class Frac {
	private int num;
	private int den;
	
	public Frac(int num, int den){
		this.num = num;
		this.den = den;
	}
	
	public Frac(int num){
		this.num = num;
		this.den = 1;
	}
	
	public Frac mult(Frac frac){
		int newNum = this.num * frac.getNum();
		int newDen = this.den * frac.getDen();
		return new Frac(newNum, newDen);
	}
	
	public Frac div(Frac frac){
		int newNum = this.num * frac.getDen();
		int newDen = this.den * frac.getNum();
		return new Frac(newNum, newDen);
	}
	
	public Frac sum(Frac frac){
		int newNum = this.num * frac.getDen() + frac.getNum() * this.den;
		int newDen = this.den * frac.getDen();
		return new Frac(newNum, newDen);
	}
	
	public Frac sub(Frac frac){
		int newNum = this.num * frac.getDen() - frac.getNum() * this.den;
		int newDen = this.den * frac.getDen();
		return new Frac(newNum, newDen);
	}
	
	public int getNum(){
		return num;
	}
	
	public int getDen(){
		return den;
	}
	
	public String toString(){
		return num + "/" + den;
	}
}
