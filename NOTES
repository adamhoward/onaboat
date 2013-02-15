# Differences from ddd-sample

## Immutablity
ddd-sample uses techniques to force immutability of certain classes:

* No no-arg constructor
* Constructor that takes all properties
* No setters
* Getters return immutable objects

Isis does not support immutability at this level. It uses annotations
to mark properties as disabled after being persisted.

## Wrapped values

ddd-sample creates classes to "wrapper" a property or properties that
are logically connected. Examples:

1. `TrackingId`, `UnLocode`: wraps a string and defines validation rules
1. `Schedule`: wraps a list of `CarrierMovement` objects
1. `RouteSpecification`: wraps an origin location, a destination
location, and an arrivalDeadline date property.

The purpose of the wrapper classes are to:

1. Use the Ubiquitous Language to represent "primitives" like `String`s
1. Treat a collection as a single object and provide methods on the
class to perform queries into the collection
1. Treat multiple properties as a single object so they can be passed
around as arguments to methods together

Isis support the first case through the use of the `@Value`
annotation and a `ValueSemanticsProvider` class.

The other two uses are not currently supported. The intent on the
persistence side is that these wrapper classes are completely invisible.
The objects can be stored as if the properties they are wrapping were
directly on the owning object. Example:

A `Cargo` has a `RouteSpecification` which has two `Location`s and a
`Date`. When persisted, the Cargo should have those two `Location`s and
a `Date` field directly on it. When the `Cargo` is "unmarshalled" from
the object store a new `RouteSpecification` object is created to hold
those properties. In this way, the `RouteSpecification` is acting as a
DDD Value object, meaning that it doesn't have its own identity even
though its properties are Entities (the two `Locations`). This is
implemented in JDO with the `@Embedded` and `@EmbeddedOnly` annotations.

On the viewer side these wrapper object should "disappear" in a
different way. Because they are values they should be embedded into the
view of their owning object rather than be represented as a link to a
separate entity. In a web viewer, this could be accomplished by directly
placing the wrapper fields inline with other fields on the owning object
perhaps in a `<fieldset>` to show that they are connected. In the RO
viewer, the wrapper object would not have to be hidden as long as its
fields were included in the representation of the owning object.
Example:

    {
      "oid": "Cargo:38^1",
      "title": "49A730E5",
      "members": [
        {
          "id": "trackingId",
          "memberType": "property",
          "value": "49A730E5"
        },
        {
          "id": "routeSpecification",
          "memberType": "property",
          "value": {
            "title": "JNTKO to CNHKG by Thu Feb 28 00:00:00 CST 2013",
            "members": [
              {
                "id": "origin",
                "memberType": "property",
                "value": {
                  "rel": "object",
                  "href": "http://localhost:8000/restful/objects/Location:9^1",
                  "method": "GET",
                  "type": "application/json;profile=\"urn:org.restfulobjects/domainobject\"",
                  "title": "Tokyo [JNTKO]"
                }
              },
              {
                "id": "destination",
                "memberType": "property",
                "value": {
                  "rel": "object",
                  "href": "http://localhost:8000/restful/objects/Location:10^1",
                  "method": "GET",
                  "type": "application/json;profile=\"urn:org.restfulobjects/domainobject\"",
                  "title": "Hong Kong [CNHKG]"
                }
              },
              {
                "id": "arrivalDeadline",
                "memberType": "property",
                "value": "20130228T060000000"
              }
            ]
          }
        }
      ]
    }

So rather than the `Cargo`'s routeSpecification having a representation of
a link to an entity:

    "value": {
      "rel": "object",
      "href": "http://localhost:8000/restful/objects/RouteSpecification:39^1",
      "method": "GET",
      "type": "application/json;profile=\"urn:org.restfulobjects/domainobject\"",
      "title": "JNTKO to CNHKG by Thu Feb 28 00:00:00 CST 2013"
    }

it has its properties embedded into the `Cargo`'s representation.