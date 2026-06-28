import type { Metadata } from "next";
import Link from "next/link";
import { Icon } from "@/components/Icon";
import { RequestCard } from "@/components/RequestCard";

export const metadata: Metadata = { title: "New Request" };

const navItems = ["Book a Ride", "Your Rides", "Profile"];

export default function DriverPage() {
  return (
    <div className="abstract-bg relative h-screen w-screen overflow-hidden">
      {/* Animated motion lines (decorative) */}
      <div className="motion-line" style={{ top: "20%" }} />
      <div className="motion-line" style={{ top: "50%", animationDelay: "-3s" }} />
      <div className="motion-line" style={{ top: "80%", animationDelay: "-6s" }} />

      {/* Top app bar */}
      <header className="fixed left-0 top-0 z-50 flex h-20 w-full items-center justify-between border-b border-outline-variant bg-surface/40 px-6 backdrop-blur-xl">
        <Link href="/" className="text-headline-xl font-bold tracking-tighter text-primary">
          Velocity
        </Link>
        <nav className="hidden items-center gap-base md:flex">
          {navItems.map((item) => (
            <button
              key={item}
              className="h-10 rounded-full px-lg text-label-md text-on-surface-variant transition-all hover:bg-surface-container-highest hover:text-on-surface"
            >
              {item}
            </button>
          ))}
        </nav>
        <div className="flex items-center gap-md">
          <button
            aria-label="Notifications"
            className="rounded-full p-base text-primary transition-colors hover:bg-surface-container-highest"
          >
            <Icon name="notifications" />
          </button>
          <div className="h-10 w-10 overflow-hidden rounded-full border border-outline-variant bg-surface-container-highest" />
        </div>
      </header>

      {/* Hero: the live request */}
      <main className="relative flex h-screen w-full items-center justify-center px-margin-mobile pt-20">
        <RequestCard />
      </main>

      {/* Mobile bottom nav */}
      <nav className="fixed bottom-0 left-0 z-50 flex w-full justify-around border-t border-outline-variant bg-surface-container-lowest/80 p-base backdrop-blur-xl md:hidden">
        {[
          { icon: "directions_car", label: "Book", active: false },
          { icon: "history", label: "Rides", active: true },
          { icon: "person", label: "Profile", active: false },
        ].map((t) => (
          <button
            key={t.label}
            className={`flex flex-col items-center gap-1 p-2 ${t.active ? "text-primary" : "text-on-surface-variant"}`}
          >
            <Icon name={t.icon} />
            <span className="text-[10px] font-bold uppercase">{t.label}</span>
          </button>
        ))}
      </nav>
    </div>
  );
}
