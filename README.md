# internet-shop 
<ul>
  <li>Create your own repository</li>
  <li>Create models: Item, User, Bucket, Order.</li>
  <li>Create DAO and service layer for Item model.</li>
  <li>Add CRUD operations into ItemDao.</li>
  <li>Use Storage class as a persistence layer.</li>
  <li>Do not forget to use your own annotation Dao.</li>
  <li>Use some static variable that will incrementally generate you an id for each model</li>
  <li>Return <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html">Optional</a> when you want return null in DAO. 
For example <code class="language-plaintext highlighter-rouge">public Optional&lt;User&gt; get(Long id);</code></li>
  <li>Add class Application with main method where you are invoking all your methods from service</li>
</ul>