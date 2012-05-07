# wicket-validation

Wicket JSR-303 (Bean Validation)

## Presentation
This project aims at integrating the JSR-303 Bean Validation spec into Wicket. To achieve that purpose, it provides custom validators which can be applied on Forms or FormComponents.

See the project announcement here : [Wicket JSR303 Validators](http://blog.zenika.com/index.php?post/2010/02/24/Wicket-JSR-303-Validators)

## Features

### Currently supported :

* Annotations
* Validation on all or a single form components
* Annotations inheritance from super classes and interfaces
* Validation of nested forms
* Validation of objects graph with the @Valid annotation
* Group validation and '@GroupSequence' annotation

### In progress :

* Custom error messages with a custom message interpolator
* Client side validation with JavaScript
* Class level validation
* Other models than AbstractPropertyModel and CompoundPropertyModel
* More FormComponent types, other than AbstractTextComponent
* Supports annotations on methods ?


