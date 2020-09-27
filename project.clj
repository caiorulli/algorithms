(defproject algorithms "0.1.0-SNAPSHOT"
  :description "Algorithms studies in Clojure"
  :url "http://github.com/caiorulli/algorithms"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns algorithms.nqueens}
  :jvm-opts ["-Djdk.attach.allowAttachSelf"
             "-XX:+UnlockDiagnosticVMOptions"
             "-XX:+DebugNonSafepoints"]
  :main algorithms.core
  :profiles {:dev {:dependencies [[midje "1.9.9"]
                                  [com.clojure-goes-fast/clj-async-profiler "0.1.0"]]
                   :plugins      [[lein-cljfmt "0.6.4"]
                                  [lein-midje "3.2.1"]]
                   :cljfmt       {:indents {fact  [[:block 2]]
                                            facts [[:block 2]]}}}})
