# MotherBox

A [re-frame](https://github.com/Day8/re-frame) bookmarks application with material design.
 
> Mother Box can explain it better than I. She is a living computer connected to the Source.
>
> -- Orion 

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```

You can then use the `./resources/public` folder with the compiled files.

## Links
* http://www.material-ui.com/
