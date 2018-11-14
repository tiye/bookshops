
(ns build.upload
  (:require ["child_process" :as child-process]))

(def configs {:orgization "webcityim"
              :name "bookshops"
              :cdn "bookshops"})

(defn sh! [command]
  (println command)
  (println (.toString (child-process/execSync command))))

(defn -main []
  (sh! (str "rsync -avr --progress dist/* tiye.me:cdn/" (:cdn configs)))
  (sh!
    (str "rsync -avr --progress dist/{index.html,manifest.json,icons} tiye.me:repo/"
      (:orgization configs) "/"
      (:name configs) "/")))
