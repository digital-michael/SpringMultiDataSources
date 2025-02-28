# LESSONS LEARNED

- for Log: if the properties are duplicated (as in #Data uncommented), the startup for log stops
- for Log: if the dataSource() is defined, it doesn't access the properties it appears.
- for all: regardless of @Profile, the databases are created using (the first?) database configuration and tables are created event if the entities are not on profile
