using System;

public interface RequestHandler
{
    public bool CanHandle(Object obj);
    public void Handle(Object obj);
}
