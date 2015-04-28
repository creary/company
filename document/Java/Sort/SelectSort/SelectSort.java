package SelectSort;

import java.util.Arrays;

public class SelectSort {
	
	public static void selectSort(DataWrap[] data){
		System.out.println("开始排序");
		int arrayLength=data.length;
		for(int i=0;i<arrayLength-1;i++){
			for(int j=i+1;j<arrayLength;j++){
				if(data[i].compareTo(data[j])>0){
					DataWrap tmp=data[i];
					data[i]=data[j];
					data[j]=tmp;
				}
			}
			System.out.println(Arrays.toString(data));
		}
	}
	public static void main(String[] args) {
		DataWrap [] data={
				new DataWrap(21,""),
				new DataWrap(30,""),
				new DataWrap(49,""),
				new DataWrap(30,"*"),
				new DataWrap(16,""),
				new DataWrap(9,"")
				};
		System.out.println("排序前:\n"+Arrays.toString(data));
		selectSort(data);
		System.out.println("排序后:\n"+Arrays.toString(data));
	}
}


