import java.util.* ;
/**
 * 用來將DotCom與GameHelper兩class在這交互使用。
 * 
 * @陳力維
 * @2019/5/25
 */
public class DotComBust
{
    private GameHelper helper = new GameHelper() ;
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>() ;
	private int numOfGuesses = 0 ;
	
    private void setUpGame() {
		// first make three dot coms and give them locations
		DotCom one = new DotCom() ;
		one.setName( "Pets.com" ) ;
		DotCom two = new DotCom() ;
		two.setName( "Toys.com" ) ;
		DotCom three = new DotCom() ;
		three.setName( "Go.com" ) ;
		dotComsList.add(one) ;
		dotComsList.add(two) ;
		dotComsList.add(three) ;
		
		System.out.println( "Your goal is to sink three dot coms." ) ;
		System.out.println( "Pets.com, Toys.com, Go.com" ) ;
		System.out.println( "Try to sink them all in the fewest number of guesses" ) ;
		
		for ( DotCom dotComToSet : dotComsList ) {							// 走訪dotComList
			ArrayList<String> newLocation = helper.placeDotCom(3) ;			// 設dotcom位置
			dotComToSet.setLocationCells( newLocation ) ;
		} // for
	} // set up game
	
	private void startPlaying() 
	{
		while( !dotComsList.isEmpty() ) {									// 判斷dotcom的list是否為空的
			String userGuess = helper.getUserInput( "Enter a guess" ) ;		// 取得玩家輸入
			checkUserGuess( userGuess ) ;
		} // while
		
		finishGame() ;
	} // start playing
	
	private void checkUserGuess( String userGuess ) {
		numOfGuesses ++ ;												// 遞增玩家猜測次數
		String result = "miss" ;											// 先假設沒有命中
		
		for ( int x = 0 ; x < dotComsList.size() ; x ++ ) {					// 對list中所有的dotcom走訪
			result = dotComsList.get(x).checkYourself( userGuess ) ;		// 要求dotcom檢查是否命中或擊沉
			
			if ( result.equals( "hit" ) ) {									// 找到被打到的船了，跳出迴圈
				break ;
			} // if
			
			if ( result.equals( "kill" ) ) {
				dotComsList.remove(x) ;
				break ;
			} // if
		} // for
		
		System.out.println( result ) ;
	} // check user guess
	
	private void finishGame() 
	{
		System.out.println( "All Dot Coms are dead! Your stock is now worthless." ) ;
		if ( numOfGuesses <= 18 ) {
			System.out.println( "It only took you " + numOfGuesses + " guesses.") ;
			System.out.println( "You got out before your options sank." ) ;
		} // if
		else {
			System.out.println( "Took you long enough. " + numOfGuesses + " guesses." ) ;
			System.out.println( "Fish are dacing with your options." ) ;
		} // else
	} // finish game

	public static void main (String[] args) 
	{
		DotComBust game = new DotComBust() ;
		game.setUpGame() ;
		game.startPlaying() ;
	} // main
} // class dotcombust
