import java.util.* ;
/**
 * 一抽象class描述船的相同行為及相同屬性。
 * 
 * @陳力維
 * @2019/5/25
 */
public class DotCom
{
	private GameHelper helper = new GameHelper() ;
    private ArrayList<String> locationCells ;				// 保存位置的arraylist
	private String name ;

	public DotCom( String n, int size ) {					// 船的建構子，n為船的名字，size為船的大小
		name = n ;
		locationCells = helper.placeDotCom( size ) ;
	} // DotCom
	
    public void setLocationCells( ArrayList<String> loc ) {	// 更新DotCom位置的setter
		locationCells = loc ;
	} // set loc
	
	public void setName( String n ) {
		name = n ;
	} // set name
	
	public String checkYourself( String userInput ) {
		String result = "miss" ;
		int index = locationCells.indexOf(userInput) ;		// 若userInput猜中了，會回傳猜中的位置，沒有則回傳-1
		if ( index >= 0 ) {
			locationCells.remove(index) ;					// 刪除被猜中的元素
			
			if ( locationCells.isEmpty() ) {				// 判斷是否擊沉
				result = "kill" ;
				System.out.println( "Ouch! You sunk " + name + "	: ( " ) ;
			} // if
			else {
				result = "hit" ;
			} // else 
		} // if
	
		return result ;										// 回傳狀態
	} // checkYourself
}
