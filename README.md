# simpleapp

## 事前準備

このプロジェクトのコンパイルの前に，[pochi](https://github.com/tamada/pochi) にて，次のコマンドを入力しておく．

```shell script
$ mvn install
```

これにより，`pochi` 関連の jar ファイルが `~/.m2/repository` 以下にインストールされる．
次のファイル（pomとjar）が存在すればOK．

* `~/.m2/repository/jp/cafebabe/pochi/2.0.0/pochi-2.0.0.pom`
* `~/.m2/repository/jp/cafebabe/pochi/kunai2/2.0.0/kunai2-2.0.0.{pom,jar}`
* `~/.m2/repository/jp/cafebabe/pochi/pochi-api/2.0.0/pochi-api-2.0.0.{pom,jar}`
* `~/.m2/repository/jp/cafebabe/pochi/pochi-core/2.0.0/pochi-core-2.0.0.{pom,jar}`

## コンパイル&実行

```shell script
$ mvn package
$ java -jar target/simpleapp.jar
```

でコンパイル・実行可能．
依存ライブラリは，`target/lib` 以下にコピーし，`simpleapp.jar` からそれらjarファイルを参照するように`MANIFEST.MF`を書いている．
また，`MANIFEST.MF`に `Main-Class`属性を追加して，実行可能 jar ファイルとしている．

## Spark の動かし方

ローカルで動かす場合はspark-submitコマンドを使う．
Sparkが入っているディレクトリにいる場合は，

```shell script
$ bin/spark-submit \
  --class "jp.ac.kyoto_su.cse.simpleapp.Main" \
  --master local \
  path/to/simple-project-1.0.jar
```


## 参考

* [Mavenで実行可能jarをビルドする方法](https://qiita.com/tarosa0001/items/e019ec4daaaf54684c53)
* [Maven Jar マニフェスト設定](https://clash-m45.hatenablog.com/entry/20120616/1339871432)

