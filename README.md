# mandleview circa 1997-2002 #

Rediscovered some really old planview/mandleview code
(including the original code for parametric colormaps.)

...and it still works.

- depends on `brew install ant`
- build it with `( cd planview ; ant clean ; ant compile )`
- will need to create some missing directories to get ant tasks to work
- run it with one of
  - `mandleview.sh`
  - `colormap.sh`
  - `planview.sh`
- experiment with colormaps by tweaking `planview/src/mandleview.properties` (changes will require a recompile)
