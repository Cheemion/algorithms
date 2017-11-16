package com.algorithms.elementary;
/**
 * Discription:Tuple,拥有多个返回对象
 * Date:2016/11/14
 * Name:王启敏(Robert Wang)
 * @author Robert
 *
 */
public class Tuple {
	public static <A,B> TwoTuple<A,B> tuple (A a, B b){
		return new TwoTuple<A,B>(a, b);
	}
	public static <A,B,C> ThreeTuple<A,B,C> tuple (A a, B b, C c){
		return new ThreeTuple<A,B,C>(a, b, c);
	}
	public static <A,B,C,D> FourTuple<A,B,C,D> tuple (A a, B b, C c, D d){
		return new FourTuple<A,B,C,D>(a, b, c, d);
	}
	public static <A,B,C,D,E> FiveTuple<A,B,C,D,E> tuple (A a, B b, C c, D d, E e){
		return new FiveTuple<A,B,C,D,E>(a, b, c, d, e);
	}
	public static class Test{
		public static void main(String[] args) {
			TwoTuple<String, Integer> t = tuple("asdf", 123);
		}
	}
	
	public static class FiveTuple<A,B,C,D,E> extends FourTuple<A,B,C,D> {
		public final E five;
		public FiveTuple(A first, B second, C third, D four, E five) {
			super(first, second, third, four);
			this.five = five;
		}
		@Override
		public String toString() {
			return "FiveTuple [five=" + five + ", four=" + four + ", third=" + third + ", first=" + first + ", second="
					+ second + "]";
		}

		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode() * 37 + five.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof FiveTuple && ((FiveTuple)obj).first.equals(first) 
					&& ((FiveTuple)obj).second.equals(second)
					&& ((FiveTuple)obj).third.equals(third) 
					&& ((FiveTuple)obj).four.equals(four) 
					&& ((FiveTuple)obj).five.equals(five);
			
		}
		
	}
	public static class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
		public final D four;
		public FourTuple(A first, B second, C third, D four) {
			super(first, second, third);
			this.four = four;
		}
		@Override
		public String toString() {
			return "FourTuple [four=" + four + ", third=" + third + ", first=" + first + ", second=" + second + "]";
		}
		
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode() * 37 + four.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof FourTuple && ((FourTuple)obj).first.equals(first) && ((FourTuple)obj).second.equals(second)
					&& ((FourTuple)obj).third.equals(third) && ((FourTuple)obj).four.equals(four);
			
		}
		
	}
	public static class ThreeTuple<A,B,C> extends TwoTuple<A,B>{
		public final C third;

		public ThreeTuple(A first, B second, C third) {
			super(first, second);
			this.third = third;
		}

		@Override
		public String toString() {
			return "ThreeTuple [third=" + third + ", first=" + first + ", second=" + second + "]";
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode() * 37 + third.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof ThreeTuple && ((ThreeTuple)obj).first.equals(first) && ((ThreeTuple)obj).second.equals(second)
					&& ((ThreeTuple)obj).third.equals(third);
			
		}
		
	}
	public static class TwoTuple<A,B> {
		public final A first;
		public final B second;
		public TwoTuple(A first, B second) {
			super();
			this.first = first;
			this.second = second;
		}
		@Override
		public String toString() {
			return "TwoTuple [first=" + first + ", second=" + second + "]";
		}
	
		@Override
		public int hashCode() {
			int result = 17;
			result = result * 37 + first.hashCode();
			result = result * 37 + second.hashCode();
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof TwoTuple && ((TwoTuple)obj).first.equals(first) && ((TwoTuple)obj).second.equals(second);
			
		}
	}
}