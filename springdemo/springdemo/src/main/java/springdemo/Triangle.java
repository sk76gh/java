package springdemo;


public class Triangle implements Shape{

 private Point pointA;
 private Point pointB;
 private Point pointC;
 
 



	public void draw(){
		System.out.println("Triangle");
		System.out.println(pointA.getX()+","+pointA.getY());
		System.out.println(pointB.getX()+","+ pointB.getY());
		System.out.println(pointC.getX()+","+ pointC.getY());
	}





	public Point getPointA() {
		return pointA;
	}





	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}





	public Point getPointB() {
		return pointB;
	}





	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}





	public Point getPointC() {
		return pointC;
	}





	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}





	public void myInit(){
		System.out.println("myInit");
	}

	public void cleanup(){
		System.out.println("destroy");
	}


}
