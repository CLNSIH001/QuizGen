package QuizGen;

public class DamAVL implements Comparable<DamAVL>{

	private  String name;
	private String FSC ;
	private String DamLevel;
	public DamAVL(String name){
		this.name = name;
	}

	public DamAVL(String name,String FSC,String DamLevel){
		this.name = name;
		this.FSC = FSC;
		this.DamLevel = DamLevel;
	}
	public int compareTo(DamAVL d){
		return this.name.compareTo(d.getname());
	}
	public String toString(){
		return (name +", "+ FSC+", " + DamLevel);
	}
	public String getname(){
		return name;
	}
	public void setdam(String a){
		name = a;
	}
}
