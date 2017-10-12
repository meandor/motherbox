(ns bookmarks.db)

(def bookmark-groups
  [{:title "General"
    :items [{:title "google" :href "http://google.de"}
            {:title "heise" :href "http://heise.de"}
            {:title "otto" :href "http://otto.de"}]}

   {:title "Spaß"
    :items [{:title "kicker" :href "http://kicker.de"}
            {:title "heise" :href "http://heise.de"}
            {:title "otto" :href "http://otto.de"}]}])

(def default-db
  {:name      "Company Name"
   :bookmarks bookmark-groups
   :filter-by ""})
