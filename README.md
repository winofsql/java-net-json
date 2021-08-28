# java-net-json
JSON フォーマットのデータを WEB より取得して利用( Google Gson )

```javascript
{
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "c:\\app\\java21\\gson-2.8.7.jar"
    ]
}
```

https://maven-badges.herokuapp.com/maven-central/com.google.code.gson/gson

```java
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
```

## JSON 処理用 URL
https://lightbox.sakura.ne.jp/demo/json/syain_json.php
