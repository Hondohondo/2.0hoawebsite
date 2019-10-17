# Feature Handlers

<p>This is where the Feature Handlers will reside - i.e. TicketHandler.cs, UtilityHandler.cs, etc.</p>
<p>All handlers will have a bool CanHandle() and void Handle() method. CanHandle() is what reports back to the DispatchDelegator.cs. Handle() is service-aware and makes the appropriate service calls to process input, then returns this back to the DispatchDelegator.cs.</p>
