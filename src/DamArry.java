package QuizGen;

public class DamArry implements Comparable<DamArry>{

	private  String name;
	private String FSC ;
	private String DamLevel;
	public DamArry(String name){
		this.name = name;
	}

	public DamArry(String name,String FSC,String DamLevel){
		this.name = name;
		this.FSC = FSC;
		this.DamLevel = DamLevel;
	}
	public String toString(){
		return (name +", "+ FSC+", " + DamLevel);
	}
	public int compareTo(DamArry d){
		return this.name.compareTo(d.getname());
	}
	public String getname(){
		return name;
	}
	public void setdam(String a){
		name = a;
	}
}
