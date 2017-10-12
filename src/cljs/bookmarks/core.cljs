(ns bookmarks.core
  (:require [cljsjs.material-ui]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [bookmarks.events]
            [bookmarks.subs]
            [bookmarks.views :as views]))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
