import java.io.* ;
import java.util.* ;
/**
 * 用來讀使用者的輸入、配置三條船的位置。
 * 
 * @陳力維，參考課本
 * @2019/5/25
 */
public class GameHelper
{
    // 实例变量 - 用你自己的变量替换下面的例子
    private static final String alphabet = "abcdefg" ;
    private int gridLength = 7 ;
    private int gridSize = 49 ;
    private int [] grid = new int[gridSize] ;
    private int comCount = 0 ;

    public String getUserInput( String prompt )
    {
        String inputLine = null ;
        System.out.print( prompt + " " ) ;
        try {
            BufferedReader is = new BufferedReader( new InputStreamReader( System.in ) ) ;
            inputLine = is.readLine() ;
            if ( inputLine.length() == 0 ) 
                return null ;
        } catch ( IOException e ) {
            System.out.println( "IOException: " + e ) ;
        }
        
        return inputLine.toLowerCase() ;
    } // get user input
    
    public ArrayList<String> placeDotCom( int comSize )   // 幫船設位置
    {
        ArrayList<String> alphaCells = new ArrayList<String>() ;
        String [] alphacoords = new String [comSize] ;          // 保存字串
        String temp = null ;                                    // 暫用字串
        int [] coords = new int[comSize] ;                      // 現有字串
        int attempts = 0 ;                                      // 目前測試的字串
        boolean success = false ;                               // 找到適合的位置嗎?
        int location = 0 ;                                      // 目前起點
        
        comCount ++ ;                                           // 現在處理道地N個
        int incr = 1 ;                                          // 水平增量
        if ( ( comCount % 2 ) == 1 ) {                          // 若是單數號的
            incr = gridLength ;                                 // 垂直增量
        } // if
        
        while ( !success & attempts ++ < 200 ) {                // 主要搜尋迴圈
            location = (int) (Math.random() * gridSize) ;       // 隨機起點
            //System.out.print( "try" + location ) ;
            
            int x = 0 ;                                         // 第n個位置
            success = true ;                                    // 假定成功
            while ( success && x < comSize ) {                  // 找尋未使用的點
                if ( grid[location] == 0 ) {                    // 如果沒有使用
                    coords[x++] = location ;                    // 儲存位置
                    location += incr ;                          // 嘗試下一個點
                    if ( location >= gridSize ) {               // 超出下緣
                        success = false ;                       // 失敗
                    } // if
                    
                    if ( x > 0 && ( location % gridLength == 0 ) ) {  // 超出右緣
                        success = false ;                       // 失敗
                    } // if
                } // if
                else {                                          // 找到已經使用的位置
                    //System.out.print( " used " + location ) ;
                    success = false ;                           // 失敗
                } // else
            } // while
        } // while
        
        int x = 0 ;                                             // 將位置轉換為字串的形式
        int row = 0 ;
        int column = 0 ;
        //System.out.println("\n") ;
        while ( x < comSize ) {
            grid[coords[x]] = 1 ;
            row = (int) ( coords[x] / gridLength ) ;
            column = coords[x] % gridLength ;
            temp = String.valueOf( alphabet.charAt(column) ) ;
            
            alphaCells.add( temp.concat(Integer.toString(row) ) ) ;
            x ++ ;
            System.out.print( " coord " + x + " = " + alphaCells.get(x-1) ) ;
        } // while
        
        //System.out.println("\n") ;
        return alphaCells ;
    } // place dot com
} // game helper
