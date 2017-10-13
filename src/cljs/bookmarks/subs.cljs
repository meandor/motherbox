(ns bookmarks.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [clojure.string :as s]))

(re-frame/reg-sub
  :name
  (fn [db]
    (:name db)))

(defn filter-links [substring links]
  (filter #(s/includes? (s/lower-case (:title %)) (s/lower-case substring)) links))

(re-frame/reg-sub
  :filter-value
  (fn [db]
    (:filter-by db)))

(re-frame/reg-sub
  :filtered-bookmarks
  (fn [db]
    (->> (:bookmarks db)
         (map (fn [bookmark-group] (update-in bookmark-group [:items] (partial filter-links (:filter-by db)))))
         (filter #(> (count (:items %)) 0)))))
