package QuizGen;

public class TNode<dataType>{
	
	DamArry data;
	TNode<dataType> left, right;

	public TNode (DamArry d, TNode<dataType> l, TNode<dataType> r){
			data = d;
			left = l;
			right = r;
		}
	
	public DamArry getData(){
		return data;
	}
		
	public TNode<dataType> getLeft(){
		return left;
		}
		
	public TNode<dataType> getRight(){
		return right;
		}
		
	}



