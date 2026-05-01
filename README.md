# Scala Cheat Sheet

https://ondeoma.github.io/scala-cheat-sheet/

Scalaのチートシート(リファレンスに近いものかもしれない)です.  
現状高カインド型のメソッドを中心に図で説明しようと試みています.

## Dependencies

* Sbt
* Node.js 24系

## 起動

Scala.jsを連続的にビルドしつつ、  
Viteでホスティングするのが良さそうです.

### Scala.js

```
sbt ~fastLinkJS
```

cf. [Scala.js](https://www.scala-js.org/)

### Vite

```
# yarnが無ければ取得
npm -g install yarn

# nodeモジュール取得
yarn

# 実行
yarn dev
```

cf. [Vite](https://ja.vite.dev/)

## その他

`resources/*.excalidraw`にて`Excalidraw`で作った図データを置いています。

cf. [Excalidraw](https://github.com/excalidraw/excalidraw)
