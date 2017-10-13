(ns bookmarks.subs
  (:require [re-frame.core :as re-frame]
            [clojure.string :as s]))

(re-frame/reg-sub
  :name
  (fn [db]
    (:name db)))

(re-frame/reg-sub
  :filter-value
  (fn [db]
    (:filter-by db)))

(defn filter-links [filter-by links]
  (filter #(s/includes? (s/lower-case (:title %)) (s/lower-case filter-by)) links))

(defn filter-items-in-group [filter-by bookmark-group] 
  (update-in bookmark-group [:items] (partial filter-links filter-by)))

(re-frame/reg-sub
  :filtered-bookmarks
  (fn [db]
    (->> (:bookmarks db)
         (map (partial filter-items-in-group (:filter-by db)))
         (remove #(empty? (:items %))))))
