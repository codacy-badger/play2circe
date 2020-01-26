# play2circe
Generates Circe Encoder/Decoder from Play Reads/Writes:
```scala
import play2circe._

val playReads: Reads[A]   = ...
val decoder:   Decoder[A] = decoderFromReads(playReads)

val playWrites: Writes[A]  = ...
val encoder:    Encoder[A] = encoderFromWrites(playWrites)
```
