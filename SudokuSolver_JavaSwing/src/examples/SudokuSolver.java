package examples;

import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JButton;
class SudokuSolver
{

    public SudokuSolver() {
        int inde=0;
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                    {
                        mymap.put(i+""+j,inde++);
                    }
                }
    }

    
        
    
        
        static HashMap<String,Integer> mymap=new HashMap<String,Integer>();

	static int[][] arr=new int[9][9];
	static int holes=51,cou=0;

	static HashMap<Integer,HashSet> xcoord=new HashMap<Integer,HashSet>();
	static 	HashMap<Integer,HashSet> ycoord=new HashMap<Integer,HashSet>();
	static 	HashSet<String> firstrow=new HashSet<String>();
	static	HashSet<String> secondrow=new HashSet<String>();
	static	HashSet<String> thirdrow=new HashSet<String>();

	static	HashSet<String> firstcol=new HashSet<String>();
	static	HashSet<String> secondcol=new HashSet<String>();
	static	HashSet<String> thirdcol=new HashSet<String>();

	static List<String> list=new ArrayList<String>();

		
	public static void main(String[] args) throws Exception
	{
               
            int inde=0;
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                    {
                        mymap.put(i+""+j,inde++);
                    }
                }


            
		firstrow.add("00");
		firstrow.add("03");
		firstrow.add("06");

		secondrow.add("30");
		secondrow.add("33");
		secondrow.add("36");

		thirdrow.add("60");
		thirdrow.add("63");
		thirdrow.add("66");

		firstcol.add("00");
		firstcol.add("30");
		firstcol.add("60");

		secondcol.add("03");
		secondcol.add("33");
		secondcol.add("63");

		thirdcol.add("06");
		thirdcol.add("36");
		thirdcol.add("66");




		xcoord.put(0,firstrow);
		xcoord.put(1,firstrow);
		xcoord.put(2,firstrow);

		xcoord.put(3,secondrow);
		xcoord.put(4,secondrow);
		xcoord.put(5,secondrow);

		xcoord.put(6,thirdrow);
		xcoord.put(7,thirdrow);
		xcoord.put(8,thirdrow);

		ycoord.put(0,firstcol);
		ycoord.put(1,firstcol);
		ycoord.put(2,firstcol);

		ycoord.put(3,secondcol);
		ycoord.put(4,secondcol);
		ycoord.put(5,secondcol);

		ycoord.put(6,thirdcol);
		ycoord.put(7,thirdcol);
		ycoord.put(8,thirdcol);

		BufferedReader buf=new BufferedReader(new FileReader("/home/rgukt/NetBeansProjects/SudokuSolver/src/sudokusolver/grid.txt"));
		for(int i=0;i<9;i++)
		{
			String str=buf.readLine();
			for(int j=0;j<9;j++)
			{
				arr[i][j]=Integer.parseInt(str.charAt(j)+"");
			}
		}



		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(arr[i][j]==0)
				{
					list.add(i+""+j);
				}

			}
		}
                
		System.out.println(list.size());
                int index=0,flagg=0;
                sudoku sdd=new sudoku();
                while(index<list.size())
                {
                    int x=Integer.parseInt(list.get(index).charAt(0)+"");
                    int y=Integer.parseInt(list.get(index).charAt(1)+"");  
                    System.out.println(x+""+y);
                    
                    for(int ele=arr[x][y]+1;ele<=9;ele++)
                    {
                        System.out.println("im in");
                        if(arr[x][y]+1<=9 && isValid(ele,list.get(index)))
                        {
                            System.out.println("la casa de papel");
                            flagg=0;
                            System.out.println(list.get(index)+" "+ele);
                            System.out.println("bt list"+MyGridLayout.buttonList.size());
                            JButton jb=MyGridLayout.buttonList.get(mymap.get(x+""+y));
                            jb.setText(ele+"");
                            jb.setBackground(Color.GREEN);
                            arr[x][y]=ele;
                            break;
                        }
                        else
                        {
                            flagg=1;
                        }
                    }
                    if(index >=1 && flagg==1){
                        index--;
                        arr[x][y]=0;
                    }
                    else
                        index++;
                }
		for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}

	}

        static boolean isValid(int ele,String pos)
        {
            int row=Integer.parseInt(pos.charAt(0)+"");
            int col=Integer.parseInt(pos.charAt(1)+"");
            if(checkRow(ele,row) && checkCol(ele,col) && checkSmallGrid(ele,pos))
            {
                return true;
            }
            return false;
        }
        private static boolean checkRow(int ele, int x) {
            for(int i=0;i<9;i++)
		{
			if(arr[x][i]==ele){
				return false;
			}
		}
            return true;
        }

        private static boolean checkCol(int ele, int y) {
            for(int i=0;i<9;i++)
		{
			if(arr[i][y]==ele){
				return false;
			}
		}
            return true;
            
        }

        private static boolean checkSmallGrid(int ele, String start){
            
                int x=Integer.parseInt(start.charAt(0)+"");
                int y=Integer.parseInt(start.charAt(1)+"");
                HashSet<Integer> temp1=ycoord.get(y);
		String posi=null;
		for(Object str:xcoord.get(x))
		{
			if(temp1.contains(str)){
				posi=str.toString();
				break;

			}
		}
		int sum=0;
		int px=Integer.parseInt(posi.charAt(0)+"");
		int py=Integer.parseInt(posi.charAt(1)+"");
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int sx=px+i;
			int sy=py+j;
			if(arr[sx][sy]==ele){
				return false;
			}
                    }
                }
                return true;
            
        }

	
	static boolean isFilled(){
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(arr[i][j]==0)
					return false;
			}
		}
		return true;
	}
    
}