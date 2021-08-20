import java.net.*;
import java.io.*;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        try {

            // 書き込みファイル名
            String filename = "syain.txt";
            // テキストで書き込み為の準備
            BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(filename), "utf-8" ) );			

            // URL文字列
            String str = "https://lightbox.sakura.ne.jp/demo/json/syain_json.php";
            // ターゲット
            URL url = new URL( str );
            // 接続オブジェクト
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            // GET メソッド 
            http.setRequestMethod("GET");
            // 接続 
            http.connect();
            
            // UTF-8 でリーダーを作成
            InputStreamReader isr = new InputStreamReader( http.getInputStream(), "utf-8");   
            // 行単位で読み込む為の準備   
            BufferedReader br = new BufferedReader( isr );   
            // BufferedReader は、readLine が null を返すと読み込み終了   

            // 文字列作成用
            StringBuilder sb = new StringBuilder();

            String line_buffer;
            while ( null != (line_buffer = br.readLine() ) ) {   
                // StringBuilder に全体を作成
                sb.append(String.format("%s\r\n", line_buffer));
            }

            // 閉じる   
            br.close();
            isr.close();
            http.disconnect();

            // *********************************************
            // JSON の処理
            // *********************************************
            Gson gson = new Gson();
            // データ部分のみ使用
            Syain[] data = gson.fromJson( sb.toString(), Syain[].class );
            for( Syain row_data : data ) {
                // テキストファイルに書き込み( utf-8 )
                bw.write( String.format("%s\r\n", row_data.社員コード ) );
                bw.write( String.format("%s\r\n",  row_data.氏名 ) );
                bw.write( String.format("%s\r\n",  row_data.フリガナ ) );
                bw.write( String.format("%s\r\n",  row_data.所属 ) );
                bw.write( String.format("%s\r\n",  row_data.性別 ) );
                bw.write( String.format("%s\r\n",  row_data.給与 ) );
                bw.write( String.format("%s\r\n",  row_data.手当 ) );
                bw.write( String.format("%s\r\n",  row_data.管理者 ) );
                bw.write( String.format("%s\r\n",  row_data.作成日 ) );
                bw.write( String.format("%s\r\n",  row_data.更新日 ) );				
            }

            bw.close();		// BufferedWriter

        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }

    }

}