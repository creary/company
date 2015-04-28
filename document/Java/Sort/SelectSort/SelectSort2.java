package SelectSort;

import java.util.Arrays;
public class SelectSort2 {
	
	public static void selectSort(DataWrap[] data){
		System.out.println("开始排序");
		int arrayLength=data.length;
		for(int i=0;i<arrayLength-1;i++){
			int minIndex=i;
			for(int j=i+1;j<arrayLength;j++){
				if(data[minIndex].compareTo(data[j])>0){
					minIndex=j;
				}
			}
			if(minIndex!=i){
				DataWrap tmp=data[i];
				data[i]=data[minIndex];
				data[minIndex]=tmp;
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


