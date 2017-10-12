(ns bookmarks.views
  (:require [re-frame.core :as re-frame]
            [cljs-react-material-ui.core :refer [get-mui-theme color]]
            [cljs-react-material-ui.reagent :as ui]))

(defn navigate-to [link]
  (aset js/document "location" link))

(defn bookmark-card [bookmark]
  [ui/card {:class "bookmark-tile"}
   [ui/card-title {:title (:title bookmark)}]
   [ui/list
    (for [link (:items bookmark)]
      ^{:key link} [ui/list-item {:on-click #(navigate-to (:href link))
                                  :class    "link"}
                    (:title link)])]])

(defn bookmarks-cards []
  (let [bookmarks (re-frame/subscribe [:filtered-bookmarks])]
    [:section {:class "bookmarks"}
     (for [bookmark @bookmarks]
       ^{:key bookmark} [bookmark-card bookmark])]))

(defn submit-to-google [event]
  (.preventDefault event)
  (let [filter-value (re-frame/subscribe [:filter-value])]
    (navigate-to (str "https://www.google.de/search?q=" @filter-value))))

(defn search-bar []
  [:section {:class "search-bar"}
   [:form {:on-submit submit-to-google}
    [ui/text-field {:autofocus           "autofocus"
                    :floating-label-text "Search"
                    :hint-text           "Search bookmarks or press enter to google"
                    :id                  "search-input"
                    :full-width          true
                    :on-change           #(re-frame/dispatch [:filter-by (-> % .-target .-value)])}]]])

(defn header []
  (let [name (re-frame/subscribe [:name])]
    [:header
     [ui/app-bar {:title                 (str @name " Bookmarks")
                  :show-menu-icon-button false}]]))

(defn main-panel []
  [ui/mui-theme-provider
   {:mui-theme (get-mui-theme {:palette {:primary1Color (color :teal500)}})}
   [:div
    [header]
    [:main
     [search-bar]
     [bookmarks-cards]]]])

