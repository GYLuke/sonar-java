class A {
  private void f() {
    try {
    } catch (Exception e) {                     // Noncompliant
    } catch (Exception e) {                     // Noncompliant
      System.out.println(e);
    } catch (Exception e) {                     // Noncompliant
      System.out.println("foo", e.getMessage());
    } catch (Exception e) {                     // Compliant
      System.out.println("", e);
    } catch (Exception f) {                     // Noncompliant
      System.out.println("", e);
    } catch (Exception f) {                     // Compliant
      System.out.println("", f);
    } catch (Exception e) {                     // Compliant
      System.out.println("", e);
      try {
      } catch (Exception f) {                   // Noncompliant
      }
    } catch (Exception e) {                     // Noncompliant
      try {
      } catch (Exception f) {                   // Noncompliant
        System.out.println("", e);
      }
    } catch (RuntimeException e) {
      try {
      } catch (Exception f) {                   // Compliant
        System.out.println("", f);
      }
      System.out.println("", e);
    }
  }

  private void g() {
    System.out.println();
  }

  private void h() {
    try {
      /* ... */
    } catch (Exception e) {                     // Compliant
      throw Throwables.propagate(e);
    } catch (RuntimeException e) {              // Compliant - propagation
      throw e;
    } catch (Exception e) {                     // Noncompliant - exception is lost
      throw new RuntimeException("context");
    }

    try {
      /* ... */
    } catch (Exception e) {                      // Compliant
      throw new RuntimeException("context", e);
    }

    try {
    } catch (Exception e) {                      // Compliant
      throw e;
    } finally {
    }

    try {
    } catch (Exception e) {                      // Noncompliant
      int a;
    } catch (Throwable e) {                      // Noncompliant
    }

    try {
    } catch (IOException e) {                    // Compliant - checked to unchecked
      throw Throwables.propagate(e);
    }

    try {
    } catch (IOException e) {                    // Compliant
      throw new RuntimeException(e);
    } catch (Exception e) {                      // Noncompliant
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {                      // Compliant
      throw Throwables.propagate(e);
    }

    try {
    } catch (Exception e) {                      // Compliant
      throw e;
    } catch (Exception ex) {
      throw new XNIException(ex);
    }


    try {
    } catch (NumberFormatException e) {          // Compliant
      return 0;
    } catch (InterruptedExcetpion e) {           // Compliant
      /* do nothing */
    } catch (ParseException e) {                 // Compliant
    } catch (MalformedURLException e) {          // Compliant
    }

    try {
    } catch (Exception e) {                      // Compliant
       foo(someContextVariable, e);
    } catch (Exception e) {                      // Compliant
      throw (Exception)new Foo("bar").initCause(e);
    }
  }
}
