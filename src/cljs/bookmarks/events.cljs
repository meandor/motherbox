(ns bookmarks.events
  (:require [re-frame.core :as re-frame]
            [bookmarks.db :as db]))

(re-frame/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-event-db
  :filter-by
  (fn [db [_ val]]
    (assoc db :filter-by val)))
